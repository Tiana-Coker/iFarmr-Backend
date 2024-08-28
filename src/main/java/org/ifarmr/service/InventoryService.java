package org.ifarmr.service;

import org.ifarmr.entity.Inventory;
import org.ifarmr.payload.request.InventoryRequest;
import org.ifarmr.payload.response.InventoryResponse;

import java.util.List;

public interface InventoryService {

    InventoryResponse createInventory (InventoryRequest inventoryRequest, String username);

    int totalInventory(String username);
}
