package net.batmat.vmstatanalyzer;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import net.batmat.vmstatanalyzer.model.DefaultVmstatDataLoader;
import net.batmat.vmstatanalyzer.model.VmstatAnalyzer;
import net.batmat.vmstatanalyzer.model.VmstatData;
import net.batmat.vmstatanalyzer.model.VmstatDataFormatException;
import net.batmat.vmstatanalyzer.model.VmstatDataLoader;
import net.batmat.vmstatanalyzer.simpleanalyzer.SimpleAnalyzer;

import org.junit.Test;

public class VmstatTest {
	@Test
	public void testUserDominance() throws Exception {
		VmstatAnalyzer analyzer = createAnalyzer("/vmstat-user-dominance100%.txt");
		assertThat(analyzer.getReport()).containsIgnoringCase("user space is dominating");
	}

	@Test
	public void testUserDominance2() throws Exception {
		VmstatAnalyzer analyzer = createAnalyzer("/vmstat-user-dominance.txt");
		assertThat(analyzer.getReport()).containsIgnoringCase("user space is dominating");
	}

	@Test
	public void testManyCases() throws Exception {
		String classpathRoot = "/toLoad/ok/";
		URL resource = VmstatTest.class.getResource(classpathRoot);
		System.out.println(new File(resource.toURI()).getAbsolutePath());
		for (String path : new File(resource.toURI()).list()) {
			System.out.println(path);
			VmstatAnalyzer analyzer = createAnalyzer(classpathRoot + "/" + path);
			String report = analyzer.getReport();
			assertThat(report).isNotEmpty();
		}
	}

	private VmstatAnalyzer createAnalyzer(String vmstatFile) throws URISyntaxException, VmstatDataFormatException {
		VmstatDataLoader loader = new DefaultVmstatDataLoader(getClass().getResource(vmstatFile).toURI().getPath());
		VmstatData data = loader.getData();
		assertThat(data).isNotNull();
		VmstatAnalyzer analyzer = new SimpleAnalyzer(data);
		return analyzer;
	}
}
