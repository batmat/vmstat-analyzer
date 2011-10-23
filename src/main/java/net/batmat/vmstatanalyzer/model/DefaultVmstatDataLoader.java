package net.batmat.vmstatanalyzer.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DefaultVmstatDataLoader implements VmstatDataLoader {

	String[] vmstatLines;

	public DefaultVmstatDataLoader(String vmstatFilePath) throws VmstatDataFormatException {
		File vmstatFile = new File(vmstatFilePath);
		if (!vmstatFile.exists() || !vmstatFile.canRead()) {
			throw new IllegalArgumentException("File with path " + vmstatFilePath + " doesn't exist or cannot be read.");
		}
		loadLines(vmstatFile);
	}

	private void loadLines(File vmstatFile) throws VmstatDataFormatException {
		try {
			List<String[]> lines = new ArrayList<>();
			BufferedReader reader = new BufferedReader(new FileReader(vmstatFile));
			String line;
			while (null != (line = reader.readLine())) {
				addLine(lines, line);
			}
		} catch (FileNotFoundException e) {
			throw new VmstatDataFormatException("File not found", e);
		} catch (IOException e) {
			throw new VmstatDataFormatException("IO exception", e);
		}
	}

	@Override
	public VmstatDataImpl getData() throws VmstatDataFormatException {
		List<String[]> lines = new ArrayList<String[]>();
		for (String line : vmstatLines) {
			addLine(lines, line);
		}
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

	private void addLine(List<String[]> lines, String line) {
		if (canTryLine(line)) {
			lines.add(filterSpaces(line.split(" ")));
		}
	}

	private static final Pattern IGNORE_LINES_PATTERN = Pattern
			.compile("(^#.*$)|(.*-------.*)|(^.*System configuration.*$)|(^.*memory.*cpu.*$)");

	private boolean canTryLine(String line) {
		boolean keep = line.trim().length() != 0 && !IGNORE_LINES_PATTERN.matcher(line).matches();
		if (!keep) {
			System.out.println("IGNORED: " + line);
		}
		return keep;
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
