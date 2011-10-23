package net.batmat.vmstatanalyzer.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import net.batmat.vmstatanalyzer.model.VmstatData;
import net.batmat.vmstatanalyzer.model.VmstatDataLoader;

public class StringVmstatDataLoader implements VmstatDataLoader {
	private String data;

	public StringVmstatDataLoader(String data) {
		this.data = data;
	}

	public VmstatData getData() {
		List<String[]> lines = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new StringReader(data));
		String line;
//		while (null != (line = reader.readLine())) {
//			lines.add(filterSpaces(line.split(" ")));
//		}
		String[][] values = new String[lines.size() - 1][];
		for (int i = 1; i < lines.size(); ++i) {
			String[] myLine = lines.get(i);
			values[i - 1] = myLine;
		}
		VmstatDataImpl vmstatData = new VmstatDataImpl();
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
