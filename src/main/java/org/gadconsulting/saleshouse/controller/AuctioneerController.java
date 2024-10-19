package org.gadconsulting.saleshouse.controller;

import org.gadconsulting.saleshouse.model.AuctioneerData;
import org.gadconsulting.saleshouse.servicies.AuctioneerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auctioneer")
public class AuctioneerController {

    @Autowired
    private AuctioneerService auctioneerService;

    @PostMapping
    public ResponseEntity findWinner(@RequestBody AuctioneerData auctioneerRequest) {
       final String[] answer= auctioneerService.winnerBid(auctioneerRequest.getBuyers(), auctioneerRequest.getReserve());
       System.out.println(answer[0]+" "+answer[1]);
       return ResponseEntity.ok(answer);


    }
}
