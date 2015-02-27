// Static Branch Prediction
// @author Shuxin Lin 

#include <stdio.h>
#include <string.h>
#include <glib.h>

// Always taken, always not taken.
void alwaysPredictor(char *filename, char *mode) {

  // Trace file variables
  FILE *file = fopen(filename, "r");
  char branch;
  char address[20];
  int taken;

  // Check if file exists
  if(file == 0) {
    printf("Error! No file exists.\n");
    return;
  }

  
  // Misprediction variables
  int total = 0;
  int correct = 0;
  float misprediction_rate;

  // Predictor
  int predictor;

  // Initialize the predictor
  if(strcmp(mode, "taken") == 0) {
    predictor = 1;
  }
  else if (strcmp(mode, "not_taken") == 0) {
    predictor = 0;
  }

  // Read the trace file one line a time
  while(fscanf(file, "%c %s %d\n", &branch, address, &taken) == 3) {
    // Compare predictor with branch data and update misprediction data
    if(taken == predictor)
      correct++;

    total++;
  } 

  // Calculate and print misprediction rate
  misprediction_rate = (total-correct)*100.0/(float)total;
  printf("Predictor: always %s\n", mode); 
  printf("Misprediction rate: %.2f\n", misprediction_rate);

  // Close file
  fclose(file);
}


void profileGuidedPrediction(char *filename) {

  // File variables
  char branch;
  char address[20];
  int taken;

  FILE *file = fopen(filename, "r");
  FILE *file2 = fopen(filename, "r");
  
  // Check if file exists
  if ( file == 0 ) {
    printf( "Error! No file exists.\n" );
    return;
  }

  // Predictor variables
  GHashTable* hash = g_hash_table_new(g_str_hash, g_int_equal);
  int counter;
  int predictor;

  // Misprediction variables
  int total = 0;
  int correct = 0;
  float misprediction_rate;

  // Go through the trace file to construct address-specific predictions
  // Read the trace file one line a time
  while(fscanf(file, "%c %s %d\n", &branch, address, &taken) == 3) {

    // If address is not in the hash table
    if (g_hash_table_lookup_extended(hash, address, NULL, NULL) != TRUE) {

      // Insert the address into hash table and initialize the counter
      g_hash_table_insert(hash, g_strdup(address), 0);
      counter = 0;
    } 
    else {
      // Get counter value indexed with address
      counter = g_hash_table_lookup(hash, address);
    }

    // Change counter according to the branch data
    if(taken) {
      counter++;
    } else {
      counter--;
    }

    // Update value in hash table
    g_hash_table_replace(hash, g_strdup(address), counter);
  }
  
  // Rerun the trace file to test profile guided predictor
  while(fscanf(file2, "%c %s %d\n", &branch, address, &taken) == 3) {

    // Get counter indexed with address
    counter = g_hash_table_lookup(hash, address);
    
    // Address taken 50% or more of the time recorded as predicted taken
    if(counter >= 0) {
      predictor = 1;
    } else {
      // the rest predicted as not taken
      predictor = 0;
    }

    // Compare predictor with branch data and update misprediction data
    if(predictor == taken) {
      correct++;
    }
    total++;
  }

  // Calculate and print misprediction rate
  misprediction_rate = (total-correct)*100.0/(float)total;

  printf("Profile Guided Predictor:\n"); 
  printf("Misprediction rate: %.2f\n", misprediction_rate);

  // Close the files
  fclose(file);
  fclose(file2);
}

int main(int argc, char *argv[]) {

  // Check if the number of arguments is 2
  if(argc != 3) {
    printf("Usage: %s <trace file> <mode>\n", argv[0]);
    return;
  }  

  // Always Taken, Always Not Taken
  if(strcmp(argv[2], "taken") == 0 || strcmp(argv[2], "not_taken") == 0) {
    alwaysPredictor(argv[1], argv[2]);
  }

  // Profile guided prediction
  else if(strcmp(argv[2], "profile") == 0) {
    profileGuidedPrediction(argv[1]);
  }
  else {
    printf("Error! Cannot recognize the mode.\n");
  }
}