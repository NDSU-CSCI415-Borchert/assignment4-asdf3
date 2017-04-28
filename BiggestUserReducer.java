import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.text.DecimalFormat;
import java.math.BigInteger;

public class BiggestUserReducer extends Reducer<Text, Text, Text, Text>{
    static Text maxkey;
    static BigInteger max = BigInteger.ZERO;
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
	BigInteger totalValue = BigInteger.ZERO;
	for(Text value : values){
	    if(value.toString().equals("-"))
		continue;
	    else{
	    BigInteger valuetobeadded = new BigInteger(value.toString());;
	    totalValue = totalValue.add(valuetobeadded);
	    }
	    if(totalValue.compareTo(max) == 1){
		max = totalValue;
		maxkey = new Text(key); 
	    }
	}
//	cleanup(context);
//	Text g = new Text(max.toString());
//	context.write(maxkey, g);
    }
//    Text g = new Text(max.toString());
//    montext.write(maxkey, g);
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException{
	Text g = new Text(max.toString());
	context.write(maxkey, g);
    }
}
