package net.aaronbrown.wsprstatistics.dao;

import net.aaronbrown.wsprstatistics.models.WSPRSpot;

import java.util.List;

/**
 * Created by aaron on 29/12/16.
 */
public interface SpotsDao {
    List<WSPRSpot> getSpotsForCall(String callsign);

    void addSpot(WSPRSpot spot);
}
