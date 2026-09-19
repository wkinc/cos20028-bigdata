package assignment1_2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    String line = value.toString();

 // Split the line by whitespace/tab into [word, count]
    String[] tokens = line.split("\\s+");

 // Only process lines that have exactly 2 parts (word and count)
    if (tokens.length == 2) {
    // The second token should be the count number
	String possibleNumber = tokens[1];
    
    // Check every character to make sure this token is really a number
    boolean isNumber = true;
    for (char c : possibleNumber.toCharArray()){
	if(!Character.isDigit(c)){
		isNumber = false;
		break;
	}
    }

    // If it is a valid number, emit it as the key with value 1
    if (isNumber){
	context.write(new Text(possibleNumber), new IntWritable(1));
    }
  } 
}
}
