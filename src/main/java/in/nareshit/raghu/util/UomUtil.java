package in.nareshit.raghu.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UomUtil {

	private static final Logger log = LoggerFactory.getLogger(UomUtil.class);

	public void generateFreeChart(String path, List<Object[]> data) {
		log.info("Inside generateFreeChart() :");

		// 1. prepare Data Source
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (Object[] ob : data) {
			// key(String)-val(Double)
			dataset.setValue(ob[0].toString(), Double.valueOf(ob[1].toString()));
		}
		// 2. create JFreeChart object
		// Input => title, dataset
		JFreeChart chart = ChartFactory.createPieChart("UomType", dataset);

		// 3. save as Image
		try {
			ChartUtils.saveChartAsJPEG(new File(path + "/uomType.jpg"), // file location + name
					chart, // JFreeChart object
					300, // width
					300); // height
		} catch (IOException e) {
			log.error("Exception Inside generateFreeChart() :" + e);
			e.printStackTrace();
		}

	}

}
