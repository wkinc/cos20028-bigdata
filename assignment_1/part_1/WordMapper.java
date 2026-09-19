package assignment1;

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

    for (String word : line.split("\\W+")) {
      if (word.length() < 3) { // check if the word is more than 3
        continue;
      }
      
      if (Character.isDigit(word.charAt(0))){ //check if is int on the first char
    	  continue;
      }
      
      context.write(new Text(word), new IntWritable(1));
    }
  }
}


