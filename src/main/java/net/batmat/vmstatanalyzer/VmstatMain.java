package net.batmat.vmstatanalyzer;

import java.io.File;
import java.io.IOException;

import net.batmat.vmstatanalyzer.model.DefaultVmstatDataLoader;
import net.batmat.vmstatanalyzer.model.VmstatAnalyzer;
import net.batmat.vmstatanalyzer.model.VmstatData;
import net.batmat.vmstatanalyzer.model.VmstatDataFormatException;
import net.batmat.vmstatanalyzer.model.VmstatDataLoader;
import net.batmat.vmstatanalyzer.simpleanalyzer.SimpleAnalyzer;

public class VmstatMain {
	public static void main(String[] args) throws IOException, VmstatDataFormatException {
		if (args.length != 1) {
			System.err.println("Usage: command vmstatFilePath");
			System.exit(1);
		}
		String vmstatFilePath = args[0];
		File vmstatFile = new File(vmstatFilePath);
		if (!(vmstatFile.exists() && vmstatFile.canRead())) {
			System.err.println("Unable to find or read " + vmstatFilePath);
			System.exit(2);
		}
		VmstatDataLoader loader = new DefaultVmstatDataLoader(vmstatFilePath);
		VmstatData data;
		try {
			data = loader.getData();
			System.out.println(data);
			VmstatAnalyzer analyzer = new SimpleAnalyzer(data);
			System.out.println("Result:");
			System.out.println(analyzer.getReport());
		} catch (VmstatDataFormatException e) {
			System.err.println("Given vmstat format not supported");
			e.printStackTrace();
			System.exit(3);
		}
	}
}
