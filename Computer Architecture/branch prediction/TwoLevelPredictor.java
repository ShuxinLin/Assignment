// Dynamic Branch Prediction: Two-Level Correlating Predictor
// @author Shuxin Lin 

import java.io.*;

public class TwoLevelPredictor {
	
	public static void twoLevelPredictor (String filename,int number) throws IOException {
		//Read the file into buffer
		FileInputStream inputStream = new FileInputStream(filename);
		BufferedReader file = new BufferedReader(new InputStreamReader(new DataInputStream(inputStream)));
		
		// File variables
		String oneLine;
		int taken;

		// Misprediction variables
		int total = 0;
		int correct = 0;
		float misprediction_rate;
		

		// Pattern History Table
		int PHT[] = new int[(int) Math.pow(2,number)];

		// Initializing PHT
		for (int i = 0; i < Math.pow(2,number); i++) {
			PHT[i] = 1;
		}

		int predictor;

		// Global History Register
		int GHR[] = new int[number];
		
		// Initializing GHR
		for (int i = 0; i < number; i++){
			GHR[i] = 0;
		}	
		
		// Read the trace file one line a time
		while ((oneLine = file.readLine()) != null) {
			// Get branch data
			taken = Integer.parseInt(oneLine.substring(oneLine.length() - 1, oneLine.length()));
			
			// Get PHT predictor indexed with the global history
			StringBuilder sb = new StringBuilder(GHR.length);
			for (int i : GHR) {
				  sb.append(i);
				}
			String s = sb.toString();
			int decimalValue = Integer.parseInt(s, 2);
			predictor = PHT[decimalValue];

			// Compare predictor with branch data and update misprediction data
			if ((predictor/2) == taken) {
				correct++;
				}
			total++;
			
			// Update PHT
			if (taken == 1) {
				switch(predictor) {
				case 0:
					PHT[decimalValue] = 1;
					break;
				case 1:
					PHT[decimalValue] = 2;
					break;
				case 2:
					PHT[decimalValue] = 3;
					break;
				case 3:
					PHT[decimalValue] = 3;
					break;
				default:
					System.out.printf("Error! Predictor value is out of range.\n");
					return;
				}
			} else if (taken == 0) {
				switch(predictor) {
				case 0:
					PHT[decimalValue] = 0;
					break;
				case 1:
					PHT[decimalValue] = 0;
					break;
				case 2:
					PHT[decimalValue] = 1;
					break;
				case 3:
					PHT[decimalValue] = 2;
					break;
				default:
					System.out.printf("Error! Predictor value is out of range.\n");
					return;
				}
			}
			
			// GHR shift and insert last branch data
			for(int i = number-1; i > 0; i-- ) {
				GHR[i] = GHR[i-1];
			}
			GHR[0] = taken;
			
		}
		
		// Calculate and print misprediction rate
		misprediction_rate = (float) ((total-correct)*100.0/(float)total);

		System.out.printf("Two-Level Predictor:\n");
		System.out.printf("Misprediction rate: %.2f\n", misprediction_rate);

		// Close file
		file.close();
	}
	
	public static void main(String args[]) throws IOException {

		  int number =Integer.parseInt(args[1]);
		  
		  // Check if the number of arguments is 2
		  if (args.length != 2) {
			  System.out.println("Usage: java TwoLevelPredictor <trace file> <number of bits>");
			  return;
			}

		  if( number == 4 || number == 8 || number == 12 || number == 16 ) {
			  twoLevelPredictor(args[0], number);
		  }
		  else {
			  System.out.println("Error! Wrong bits.");
			}
	}
}