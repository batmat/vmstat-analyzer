package net.batmat.vmstatanalyzer.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultVmstatDataLoader implements VmstatDataLoader {

	File vmstatFile;

	public DefaultVmstatDataLoader(String vmstatPath) {
		vmstatFile = new File(vmstatPath);
		if (!vmstatFile.exists() || !vmstatFile.canRead()) {
			throw new IllegalArgumentException("File with path " + vmstatPath + " doesn't exist or cannot be read.");
		}
	}

	public VmstatData getData() throws IOException {
		List<String[]> lines = new ArrayList<String[]>();
		BufferedReader reader = new BufferedReader(new FileReader(vmstatFile));
		String line;
		while (null != (line = reader.readLine())) {
			lines.add(filterSpaces(line.split(" ")));
		}
		String[][] values = new String[lines.size()-1][];
		for (int i = 1; i < lines.size(); ++i) {
			String[] myLine = lines.get(i);
			values[i - 1] = myLine;
		}
		VmstatData vmstatData = new VmstatData();
		vmstatData.setTitles(lines.get(0));
		vmstatData.setValues(values);
		return vmstatData;
	}

	private String[] filterSpaces(String[] split) {
		List<String> list = new ArrayList<String>();
		for (String s : split) {
			if (s.trim().length() != 0)
				list.add(s);
		}
		return list.toArray(new String[list.size()]);
	}
}
