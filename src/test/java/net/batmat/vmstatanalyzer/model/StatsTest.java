package net.batmat.vmstatanalyzer.model;

import static org.fest.assertions.Assertions.assertThat;
import net.batmat.vmstatanalyzer.core.model.Stats;

import org.junit.Test;

public class StatsTest {
	@Test
	public void testStats() {
		String[] values = new String[] { "1", "5", "4", "10", "7", "2", "21" };
		// Dans l'ordre : 1,2,4,5,7,10,21
		Stats stats = new Stats(values);

		// FIXME : equality with float, not a good idea? :/
		assertThat(stats.getMin()).isEqualTo(1.0f);
		assertThat(stats.getMax()).isEqualTo(21.0f);
		assertThat(stats.getAvg()).isGreaterThanOrEqualTo(7.142f).isLessThanOrEqualTo(7.143f);
		assertThat(stats.getMean()).isEqualTo(5f);
		assertThat(stats.getMean()).isEqualTo(stats.getPercentile(50));
		assertThat(stats.getPercentile(50)).isEqualTo(5f);
		assertThat(stats.getPercentile(90)).isEqualTo(21f);
		assertThat(stats.getPercentile(95)).isEqualTo(21f);
		assertThat(stats.getPercentile(10)).isEqualTo(1f);
		assertThat(stats.getPercentile(14)).isEqualTo(1f);
		assertThat(stats.getPercentile(28)).isEqualTo(2f);
	}
}
