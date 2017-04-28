import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MostPopularPath{
    public static void main(String[] args) throws Exception {
	if(args.length != 2){
	    System.err.println("Usage: MostPopularPath <input path> <output path>");
	    System.exit(-1);
	}

	Job job = new Job();
	job.setJarByClass(MostPopularPath.class);
	job.setJobName("Most Popular Path");

	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));

	job.setMapperClass(MostPopularPathMapper.class);
	job.setReducerClass(MostPopularPathReducer.class);

	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(Text.class);

	System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
