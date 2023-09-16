package com.logistics.kk.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class ShipmentTypeUtil {

	public void generatePieChart(String path,List<Object[]> data) {
		//1. prepare Data Source
		DefaultPieDataset dataset = new  DefaultPieDataset();
		for(Object[] ob:data) {
			//key(String)-val(Double)
			dataset.setValue(
					ob[0].toString(), 
					Double.valueOf(ob[1].toString())
					);
		}

		//2. create JFreeChart object
		//Input => title, dataset
		JFreeChart chart = ChartFactory.createPieChart("SHIPMENT TYPE MODE", dataset);

		//3. save as Image
		try {
			ChartUtils.saveChartAsJPEG(
					new File(path+"/shipmentModeA.jpg"),  //file location + name
					chart,  //JFreeChart object
					300,  //width
					300); //height
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public void generateBarChart(String path,List<Object[]> data) {
		//1. prepare Data Source
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Object[] ob:data) {
			//val(Double) -- key(String)
			dataset.setValue(
					Double.valueOf(ob[1].toString()),
					ob[0].toString(),
					""//display label
					);
		}

		//2. create JFreeChart object
		//Input => title, x-axis label, y-axis-label, dataset
		JFreeChart chart = ChartFactory.createBarChart("SHIPMENT TYPE MODE", "MODES", "COUNTS", dataset);

		//3. save as Image
		try {
			ChartUtils.saveChartAsJPEG(
					new File(path+"/shipmentModeB.jpg"),  //file location + name
					chart,  //JFreeChart object
					450,  //width
					400); //height
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
