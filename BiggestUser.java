import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class BiggestUser{
    public static void main(String[] args) throws Exception {
	if(args.length != 2){
	    System.err.println("Usage: BiggestUser <input path> <output path>");
	    System.exit(-1);
	}

	Job job = new Job();
	job.setJarByClass(BiggestUser.class);
	job.setJobName("Biggest User");

	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));

	job.setMapperClass(BiggestUserMapper.class);
	job.setReducerClass(BiggestUserReducer.class);

	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(Text.class);

	System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
