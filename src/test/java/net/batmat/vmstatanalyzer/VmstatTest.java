package net.batmat.vmstatanalyzer;

import java.io.IOException;
import java.net.URISyntaxException;

import junit.framework.TestSuite;

import net.batmat.vmstatanalyzer.model.DefaultVmstatDataLoader;
import net.batmat.vmstatanalyzer.model.VmstatAnalyzer;
import net.batmat.vmstatanalyzer.model.VmstatData;
import net.batmat.vmstatanalyzer.model.VmstatDataLoader;
import net.batmat.vmstatanalyzer.simpleanalyzer.SimpleAnalyzer;

import org.junit.Test;

import static org.fest.assertions.Assertions.*;
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
	public void testSysDominance() throws Exception {
		VmstatAnalyzer analyzer = createAnalyzer("/vmstat-sys-dominating.txt");
		assertThat(analyzer.getReport()).containsIgnoringCase("system is dominating");
	}

	
	private VmstatAnalyzer createAnalyzer(String vmstatFile) throws URISyntaxException, IOException {
		VmstatDataLoader loader = new DefaultVmstatDataLoader(getClass().getResource(vmstatFile)
				.toURI().getPath());
		VmstatData data = loader.getData();

		VmstatAnalyzer analyzer = new SimpleAnalyzer(data);
		return analyzer;
	}
}
