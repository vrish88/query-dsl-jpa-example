package com.lavrisha.tracker;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SearchParams {
    public String title;
    public String author;
}
