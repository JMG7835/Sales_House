package org.gadconsulting.saleshouse.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AuctioneerData {
    private List<Buyer> buyers;
    private Buyer reserve;
}
