import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.*;

/**
 * Created by Shuxin on 15-11-10.
 */

/* 
Class to simulate the network. System design directions:

- Synchronous communication: each round lasts for 20ms
- At each round the network receives the messages that the nodes want to send and delivers them
- The network should make sure that:
	- A node can only send messages to its neighbours
	- A node can only send one message per neighbour per round
- When a node fails, the network must inform all the node's neighbours about the failure
*/

public class Network {

	private static List<Node> nodes;
	private int round;							
	private int period = 20;	// Round duration
	private BufferedWriter log = null;	// log file		

    // Map to store messages to be deliver in the next round
    // Integer for the id of the sender and String for the message
	private Map<Integer, String> msgsToDeliver;
	
	// Map to store undelivered election messages
	// Integer for round number and String for the message
	private Map<Integer, String> electionMsgs;

	// List to store failure nodes
	private LinkedList<Integer> failureNodes;
					

	/**
	 * To intialize the network, parse the input file. 
	 * @param fileName
	 */	
	public Network(String fileName) throws IOException{
		System.out.println("Initializing the network.");
		NetSimulator();
		parseFile(fileName);
		processMessage();
	}

	/**
	 * To call methods for initiating the system and producing the log.
	 */	
	public void NetSimulator() {
        nodes = new LinkedList<Node>();
		msgsToDeliver = new ConcurrentHashMap<Integer, String>();
		electionMsgs = new HashMap<Integer, String>();
		failureNodes = new LinkedList<Integer>();
		// Create log file or throw exception if fail
		try {		
			File file = new File("log.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			log = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
   	}

	/**
	 * Return the size of nodes
	 */	
	public int nodes_size() {
		return nodes.size();
	}
	

	/**
	 * Return log file
	 */	
	public BufferedWriter getLog() {
		return log;
	}
	

	/**
	 * return node with a given identifier
	 * @param id
	 */	
	public Node idToNode(int id) {
		for (Node node : nodes) {
			if (node.getNodeId() == id) {
				return node;
			}
		}
		return null;
	}


	/**
	 * return node with a given index of the nodes list
	 * @param index
	 */	
	public Node indexToNode(int index) {
		return nodes.get(index);
	}
	
	/**
	 * With a given node, return its index in the list 
	 * @param node
	 */	
	public int getIndex(Node node) {
		return nodes.indexOf(node);
	}
	
	/**
	 * return the current round number 
	 */
	public int getRound() {
		return round;
	}
	
	/**
	 * Parse input file to contruct ring topology.
	 * Three different formatted lines to be processed: "Neighbor", "Election", "Failure"
	 * Neighbor: node + its neighbor_nodes
	 * Election: ELECT + nodes to start leader election
	 * Failure:	FAIL + failure node
	 * @param fileName	input file name
	 */
	private void parseFile(String fileName) throws IOException {
		System.out.println("Contructing the ring topology.");
		// read output file into program
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
    		String line = br.readLine();
    		// skip the first line
    		line = br.readLine();
			while (line != null) {
				String[] stringArr = line.split(" ");
				String command = stringArr[0];
				// parse Neighbor
				// if the first string can be converted to integer
				if (command.matches("\\d+")) {
					int sender_id = Integer.parseInt(stringArr[0]);
					// initialize node by adding it to nodes
					nodes.add(new Node(sender_id, this));
					// try to contruct new message without sender_id
					String new_msg = "";
					for (int i = 1; i < stringArr.length; i++) {
						new_msg += stringArr[i] + " ";
					}
					// add sender_id, add its neighbors to the msgsToDeliver
					addMessage(sender_id, new_msg);	
					// prepare to add each node's neighbors
					// by delivering msg to the receiver which is also the message sender
					deliverMessage(idToNode(sender_id));				
				}
				// parse Election
				else if (command.equals("ELECT")) {    		
					int round = Integer.parseInt(stringArr[1]);
					// add round number and selected nodes into electionMsgs
					// wait until the round is on
					electionMsgs.put(round, line);
				}
				// parse Failure
				else if (command.equals("FAIL")) {
					// add failure node to the failureNodes
					// wait until the election is over
					failureNodes.add(Integer.parseInt(stringArr[1]));
				}
				
				// Read the next line of input file
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	/**
	 * At each round, the network collects all the messages that 
	 * the nodes want to send to their neighbours or their next node. 
	 * @param id receiver id
	 * @param m  message
	 */
	public synchronized void addMessage(int id, String m) {
		System.out.println("Round " + round + " message added: " + "(" + id + ", " + m + ")");
		// if message with the same receiver exists in the Map, then deliver it.
		if (msgsToDeliver.containsKey(id)) {
			deliverMessage(idToNode(id));
		}
		// put (receiver_id, message) in the Map
		// ready to deliver
		msgsToDeliver.put(id, m);
	}

	/**
	 * main function: process all the messages in each round, 
	 * send message to the node or wait until the right round
	 * There are three containers storing message info:
	 * msgsToDeliver, electionMsgs, failureNodes
	 * A node can send only to its neighbours, one message per round per neighbour.
	 * program stops until the three containers are all empty
	 */
	public synchronized void processMessage() {
		// loop until three containers are all empty
		while(!msgsToDeliver.isEmpty() || !electionMsgs.isEmpty() || !failureNodes.isEmpty()) {
			// record the start time of each round
			long start_time = System.currentTimeMillis();
			// check once per round to guarantee each node sends only one message per round 
			boolean round_end = false;
			while (System.currentTimeMillis() < (start_time + period)) {
				// if this is the first time to process message at this round
				if (round_end == false) {
					// if election message exists for this round
					if (!electionMsgs.isEmpty() && electionMsgs.containsKey(round)) {
						processElectionMsg(round);
					}
					// deliver messages in the msgsToDeliver to their receivers
					if (!msgsToDeliver.isEmpty()) {
						for (int id : msgsToDeliver.keySet()) {
							Node node = idToNode(id);
							// deliver message to the receiver and ask receiver for responding
							deliverMessage(idToNode(id));
							node.sendMsgBack();
						}
					}

					// if only failure messages are unprocessed
					if (msgsToDeliver.isEmpty() && electionMsgs.isEmpty() && !failureNodes.isEmpty()) {
						processFailureMsg();
					}

				// round number increments and set this round end
				round++;
				round_end = true;
				}
			}
			// enter next round when time out
			round_end = false;
		}

		// after every message is processed, close log file
		try {
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * if the election message is triggered, 
	 * send message to every elected node to initialize leader election
	 * @param round
	 */	
	public void processElectionMsg (int round) {
		String message = electionMsgs.get(round);
		String[] stringArr = message.split(" ");
		String command = stringArr[0];
		if (command.equals("ELECT")) {
			for (int i = 2; i < stringArr.length; i++) {
				int id = Integer.parseInt(stringArr[i]);
				// id is the elected node id
				addMessage(id, "INIT_ELECTION");
			}			
		}
		electionMsgs.remove(round);
	}

	/**
	 * if failure message is triggered,
	 * deliver message to the failure node each round and ask it and network for responding
	 */	
	public void processFailureMsg () {
		// retrieve the first node in failureNodes
		int failure_id = failureNodes.getFirst();
		Node failure_node = idToNode(failure_id);
		addMessage(failure_id, "FAIL");
		deliverMessage(failure_node);
		failure_node.sendMsgBack();
		// remove delivered node(first node) in failureNodes
		failureNodes.remove();
	}

	/**
	 * deliver message to the receiver given receiver node is provided
	 * @param node receiver node
	 */
	public synchronized void deliverMessage(Node node) {
		if (node != null) {
			int id = node.getNodeId();
			// get message by key indexing 
			String message = msgsToDeliver.get(id);
			System.out.println("Round " + round + " message delivered: " + "(" + id + ", " + message + ")");
			node.receiveMsg(message);
			msgsToDeliver.remove(id);
		}	
	}

	/**
	 * to inform the neighbours of a failed node about the event
	 * @param id failure node id
	 */
	public synchronized void informNodeFailure(int id) {
		Node failure_node = idToNode(id);
		// broadcast the failure message to to its neighbours
		failure_node.broadcast("Your Neighbor " + id + " fail");
		// remove failure node from nodes
		nodes.remove(failure_node);
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		if (args == null || args.length == 0) {
			System.out.println("Proper Usage is: ./run.sh input.txt");
			System.exit(0);
		}
		Network network = new Network(args[0]);
		
	}	
}
