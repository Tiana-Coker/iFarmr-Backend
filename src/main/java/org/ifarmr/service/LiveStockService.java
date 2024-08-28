package org.ifarmr.service;

import org.ifarmr.payload.request.LiveStockRequest;
import org.ifarmr.payload.response.LiveStockResponse;

public interface LiveStockService {
    LiveStockResponse addLiveStock(LiveStockRequest liveStockRequest, String username);
}
