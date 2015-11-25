
## Implementation Decision

### Parse input file and construct ring topology

The input file is read into program by line. After analysing the first word of each line, lines are divided into three types of messages - Neighbor, Election, Failure. Message Neighbor is to construct the original network graph and ring ordering the same sequence as input. The formatted messages are added to the msgsToDeliver which stores <id, message> pairs to be delivered in the next round. Message ELECT is to initialize leader selection. The formatted messages are put into the electionMsgs which stores <round, electedNodes> pairs that is added to msgsToDeliver in the specific round. Message FAIL is to add nodes that will fail into failureNodes.

### Process message at one round

There are three containers storing messages - msgsToDeliver, electionMsgs, failureNodes. At each round, the program will go through these three containers. electionMsgs should be check first. And then clear msgsToDeliver. After the first two containers are both empty, the failure node in failureNodes will be formatted into messages which are added to msgsToDeliver. In the end if all containers are empty, we stop to close log file.

> how to guarantee each node sends only one message per round?
msgsToDeliver and flag round_end are both used to ensure this issue. msgsToDeliver is a hashmap whose key is node_id so only one message is stored and processed by indexing node_id in the hashmap. Flag round_end ensures the hashmap will only be accessed once at every round. After the access is used for each round, round_end is set to be true which indicates this round is over. 

### parse message when received by node
This method is implemented in Node Class. There are six types of messages, integer, INIT_ELECTION, ELECT, LEADER, FAIL, Your, which can be received by one node. INIT_ELECTION is to initialize leader election from the elected node by adding message formatted with "ELECT #node" which will eventually becoming ELECT messages. Message ELECT is to implement Chang and Roberts Algorithm. If the leader is elected, then write to log file. Message LEADER is to broadcast melection result to the neighbors and to the whole network in the end. Message FFAIL is to broadcast failure message to its neighbors using informNodeFailure implemented in Network Class. Message Your is to remove failed neighbor node when receiving broadcast message from it.

> How to ensure the node processes only one message at each round?
We maintain a LinkedList to store incoming messages. When the node is called to respond by network, the node first check the list and if it is not empty, then retrieve the first message and process, and remove it. At each round, every node will be called at most once so only one message can be processed.

### Node Failure in Part B

When the FAIL message is received, the node broadcast the message to its neighbors and then check its leader status. If he is the leader, then initialize the leader election immediately. Otherwise, just do broadcast.

In this program, we assume once the leader fails, its previous node and next node are neighboring relationship. So all we need to do is to remove failure node from the nodes list, so the ring will reconstruct automatically.

However, this is not the case all the time. Sometimes the ring cannot be reconstructed after removing failure node. This is because the network may be no longer linked. It is much more complex, so we ignore this situation.



