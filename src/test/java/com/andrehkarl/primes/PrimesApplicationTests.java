package com.andrehkarl.primes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.net.URI;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PrimesApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void verifyPrimeNumbersUpTo_7() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/primes/7",
                String.class)).isEqualTo("{\"initial\":7,\"primes\":[2,3,5,7]}");
    }

    @Test
    public void verifyPrimeNumbersUpTo_10_parallelalgo() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/primes/10?parallelalgo=false",
                String.class)).isEqualTo("{\"initial\":10,\"primes\":[2,3,5,7]}");
    }

    @Test
    public void verifyPrimeNumbersUpTo_1009_parallelalgo () throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/primes/1009?parallelalgo=true",
                String.class)).isEqualTo("{\"initial\":1009,\"primes\":["
                + "2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,"
                + "107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,"
                + "223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,"
                + "337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,"
                + "457,461,463,467,479,487,491,499,503,509,521,523,541,547,557,563,569,571,577,587,"
                + "593,599,601,607,613,617,619,631,641,643,647,653,659,661,673,677,683,691,701,709,"
                + "719,727,733,739,743,751,757,761,769,773,787,797,809,811,821,823,827,829,839,853,"
                + "857,859,863,877,881,883,887,907,911,919,929,937,941,947,953,967,971,977,983,991,"
                + "997,1009]}");
    }

    @Test
    public void verifyPrimeNumbersUpTo_1009() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/primes/1009?parallelalgo=false",
                String.class)).isEqualTo("{\"initial\":1009,\"primes\":["
                + "2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,"
                + "107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,"
                + "223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,"
                + "337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,"
                + "457,461,463,467,479,487,491,499,503,509,521,523,541,547,557,563,569,571,577,587,"
                + "593,599,601,607,613,617,619,631,641,643,647,653,659,661,673,677,683,691,701,709,"
                + "719,727,733,739,743,751,757,761,769,773,787,797,809,811,821,823,827,829,839,853,"
                + "857,859,863,877,881,883,887,907,911,919,929,937,941,947,953,967,971,977,983,991,"
                + "997,1009]}");
    }

    @Test
    public void verifyPrimeNumbersUpTo_10_XML() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String body= "";
        RequestEntity request = RequestEntity
                .get(new URI("http://localhost:" + port + "/primes/10"))
                .accept(MediaType.APPLICATION_XML)
                .build();
        assertThat(this.restTemplate.exchange(request,
                String.class).getBody()).isEqualTo("<Primes><initial>10</initial><primes><primes>2</primes><primes>3</primes><primes>5</primes><primes>7</primes></primes></Primes>");
    }
}
