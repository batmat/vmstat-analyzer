package net.batmat.vmstatanalyzer;

import java.io.IOException;

import net.batmat.vmstatanalyzer.model.DefaultVmstatDataLoader;
import net.batmat.vmstatanalyzer.model.VmstatAnalyzer;
import net.batmat.vmstatanalyzer.model.VmstatData;
import net.batmat.vmstatanalyzer.model.VmstatDataLoader;
import net.batmat.vmstatanalyzer.simpleanalyzer.SimpleAnalyzer;

public class VmstatMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		VmstatDataLoader loader = new DefaultVmstatDataLoader("C:/Users/mathus/Dropbox/mipih/01_projets_en_cours/09-tests-perf-marches/marches-perf-jmeter/40users-etape2/vmstat-40users-simple.txt");
		VmstatDataLoader loader = new DefaultVmstatDataLoader("vmstat-1.txt");
		VmstatData data = loader.getData();
		
		System.out.println(data);
		
		VmstatAnalyzer analyzer = new SimpleAnalyzer(data);
		System.out.println("Result");
		System.out.println(analyzer.getReport());
	}

}
