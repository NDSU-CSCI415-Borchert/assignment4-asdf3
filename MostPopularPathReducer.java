import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.net.URL;

public class MostPopularPathReducer extends Reducer<Text, Text, Text, Text>{
    static int count = 0;
    static Text path;  
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
	int icount = 0;
	for(Text value : values){
//	    URL youareell = new URL(value.toString());
	    icount += 1;
	    if(icount > count){
		count = icount;
		path = new Text(key);
	    }
	}
//	Text t = new Text(totalValue.toString());
//	context.write(key, t);
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException{
	Text g = new Text(Integer.toString(count));
	context.write(path, g);
    }
}
