package org.gadconsulting.saleshouse.services;

import org.gadconsulting.saleshouse.model.Buyer;
import org.gadconsulting.saleshouse.servicies.AuctioneerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AuctioneerTest {

    @InjectMocks
    private AuctioneerService auctioneer = new AuctioneerService();

    @Test
    public void mainAuctionnerTest() {
        List<Buyer> buyers = new ArrayList<>();
        List<Integer> bids5 = new ArrayList<>();

        Buyer reserve = Buyer.builder().name("reserve").bids(List.of(100)).build();
        buyers.add(Buyer.builder().name("A").bids(List.of(110, 130)).build());
        buyers.add(Buyer.builder().name("B").build());
        buyers.add(Buyer.builder().name("C").bids(List.of(125)).build());
        buyers.add(Buyer.builder().name("D").bids(List.of(105, 115, 90)).build());
        buyers.add(Buyer.builder().name("E").bids(List.of(132, 135, 140)).build());


        buyers.add(Buyer.builder().name("E").bids(bids5).build());

        final String[] answer = auctioneer.winnerBid(buyers, reserve);
        assertThat(answer[0]).isEqualTo("E");
        assertThat(answer[1]).isEqualTo("130");
    }
}
