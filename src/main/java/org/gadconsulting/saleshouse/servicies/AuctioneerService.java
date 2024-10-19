package org.gadconsulting.saleshouse.servicies;

import org.gadconsulting.saleshouse.model.Buyer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AuctioneerService {

    public String[] winnerBid(List<Buyer> buyers, Buyer reserve) {
        String[] winner = new String[2];
        List<Integer> allPrice = new ArrayList<>();
        AtomicInteger bidReserve= new AtomicInteger(0);
        AtomicInteger betterPrice= new AtomicInteger(0);
        if (reserve != null && !reserve.getBids().isEmpty()) {
            bidReserve.set(reserve.getBids().get(0));
            winner[0] = reserve.getName();
            winner[1] = reserve.getBids().get(0).toString();
        }
            buyers.forEach(buyer -> {
                if(bidsIsPresent(buyer) && !buyer.getName().equals(winner[0])) {
                    buyer.getBids().forEach( bid ->{
                        allPrice.add(bid);
                        if (bid >= bidReserve.get() && bid > betterPrice.get()) {
                            betterPrice.set(bid);
                            winner[0] = buyer.getName();
                        }
                    });

                }
            });


        Collections.sort(allPrice);
        winner[1] = ""+getPriceBeford(allPrice, betterPrice);
        return winner;
    }

    private static int getPriceBeford(List<Integer> allPrice, AtomicInteger betterPrice) {
        return allPrice.stream().mapToInt(price -> {
            if (price < betterPrice.get()) {
                return price;
            }
            return 0;
        }).max().orElse(0);
    }

    private static boolean bidsIsPresent(Buyer buyer) {
        return buyer.getBids() != null && !buyer.getBids().isEmpty();
    }

    private static int getMaxBid(List<Buyer> buyers) {
        return buyers.stream().mapToInt(buyer -> {
            if (bidsIsPresent(buyer)) {
                return buyer.getBids().size();
            }
            return 0;
        }).max().orElse(0);
    }
}
