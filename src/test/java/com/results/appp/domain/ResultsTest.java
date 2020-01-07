package com.results.appp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.results.appp.web.rest.TestUtil;
import java.util.UUID;

public class ResultsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Results.class);
        Results results1 = new Results();
        results1.setId(UUID.randomUUID());
        Results results2 = new Results();
        results2.setId(results1.getId());
        assertThat(results1).isEqualTo(results2);
        results2.setId(UUID.randomUUID());
        assertThat(results1).isNotEqualTo(results2);
        results1.setId(null);
        assertThat(results1).isNotEqualTo(results2);
    }
}
