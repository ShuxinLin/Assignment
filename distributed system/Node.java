import java.util.*;
import java.io.*;
import java.lang.*;
/**
 * Created by Shuxin on 15-11-10.
 */

/* Class to represent a node. Each node must run on its own thread.*/

public class Node extends Thread {

	
	private int id;	
	private boolean participant = false;
	private boolean isLeader = false;
	private Network network;		
	
	private Node leader = null;		// hold the node's leader

	public LinkedList<Integer> myNeighbours; 	// Neighbouring nodes
	public LinkedList<String> incomingMsg; 		// Queues for the incoming messages
	
	public Node(int id, Network network){	
		this.id = id;
		this.network = network;        
		
		myNeighbours = new LinkedList<Integer>();
		incomingMsg = new LinkedList<String>();
	}
		
	// Basic methods for the Node class

	/**
	 * Method to get the Id of a node instance
	 */
	public int getNodeId() {
		return id;
	}

	/**
	 * Method to get the neighbours of the node
	 */
	public LinkedList<Integer> getNeighbors() {
		return myNeighbours;
	}
		
	/**
	 * Method to add a neighbour to a node
	 * @param id 
	 */
	public void addNeighbour(int id) {
		// Append node n to myNeighbour list
		myNeighbours.addFirst(id);
	}


	/**
	 * Method that implements the reception of an incoming message by a node
	 * @param m 
	 */
	public void receiveMsg(String m) {
		// Append the new message to incoming
		incomingMsg.add(m);
	}

	/**
	 * Send messages back by parsing different messages
	 * There are 6 types of messages with different command(the first string)
	 * if command is :
	 * integer, 		then add neighbors
	 * INIT_ELECTION,   then initialize leader election
	 * ELECT, 			then leader is elected if id matches, or send message with larger id
	 * LEADER, 			then broadcast leader status to its neighbors
	 * FAIL, 			then intitialize election if leader fails, or do nothing
	 * Your, 			then remove the failure node from its neighbors
	 */
	public void sendMsgBack() {
		while (!incomingMsg.isEmpty()) {
			String msg = incomingMsg.removeFirst();
			if (!msg.isEmpty()) {
				String[] messageArr = msg.split(" ");
				String command = messageArr[0];
				int next_node_id = getNextNodeId();
				String new_msg = "";
				
				// if command is adding neighbors
				if (command.matches("\\d+")) {
					for (int i = 0; i < messageArr.length; i++) {
						addNeighbour(Integer.parseInt(messageArr[i]));
					}
				}
				
				// initialize leader election
				else if (messageArr[0].equals("INIT_ELECTION")) { 
					new_msg = "ELECT " + id;
					System.out.println("Round " + network.getRound() +  " node " + id + " starts leader election");
					participant = true;				
					network.addMessage(next_node_id, new_msg);				
				}

				// receive election message from previous node
				else if (command.equals("ELECT")) {				
					int msg_id = Integer.parseInt(messageArr[1]);
					// leader is selected
					if (msg_id == id) {
						System.out.println("Round " + network.getRound() + " new leader: node " + id);
						// broadcast new leader to its neighbors
						new_msg = "LEADER " + id;
						isLeader = true;
						participant = false;
						this.leader = this;
						broadcast(new_msg);
						
						// write leader election result to log file
						try {
							this.network.getLog().write("Round " + network.getRound() + " LEADER " + id + "\n");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else {
						// if node is non-participant, then send the max id to the next
						if (!participant) {
							new_msg = "ELECT " + Math.max(msg_id,id);
							participant = true;	
							network.addMessage(next_node_id, new_msg);
						}
						// if node is participant
						else {
							// if msg_id is larger, the replace message with it. Otherwise, do nothing 
							if (msg_id > id) {
								new_msg = "ELECT " + msg_id;
								network.addMessage(next_node_id, new_msg);
							}
						}
					}
				}
				// if leader broadcast message is received, broadcast leader to its neighbors
				else if (command.equals("LEADER")) {
					int id = Integer.parseInt(messageArr[1]);
					leader = network.idToNode(id);
					if (participant == true) {
						broadcast(msg);					
					}
					participant = false;
				}
				// if failure message is received
				else if (command.equals("FAIL")) {
					System.out.println("Round " + network.getRound() + " node " + id + " fail");
					network.informNodeFailure(getNodeId());
					// if failure node is the current leader, then initialize election. Otherwise, do nothing
					if (isLeader) {
						network.addMessage(next_node_id, "INIT_ELECTION");
					}
				
				}
				// if one of the neighbor failes, then remove it from myNeighbours
				else if (command.equals("Your")) {
					int failure_id = Integer.parseInt(messageArr[2]);
					this.myNeighbours.removeFirstOccurrence(failure_id);
				}
			}
		}
	}
		
	/**
	 * return the id of next node in the ring
	 */
	public int getNextNodeId() {
		int next_index = network.getIndex(this) + 1;
		if (next_index == network.nodes_size()) {
			// in the ring, the next index of last node is 0
			next_index = 0;
		}
		return network.indexToNode(next_index).getNodeId();
	}
	
	/**
	 * send a message its neighbours
	 * @param m
	 */
	public void broadcast(String m) {
		for (int id : myNeighbours) {
			network.addMessage(id, m);
		}
	}
	
}