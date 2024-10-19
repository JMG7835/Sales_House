package org.gadconsulting.saleshouse.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class Buyer {
    private String name;
    private List<Integer> bids;
}
