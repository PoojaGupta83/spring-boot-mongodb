package com.programmingexpense.springbootmongodb.utils;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class MeterUtils {
    public Document projectionForMeterById() {
        Document projection = new Document();

        projection.put("DeviceID", 1);
        projection.put("Clock", 1);
        projection.put("CumBillingCount", 1);
        projection.put("CumEnergyWh", 1);
        projection.put("CumEnergyWhEx", 1);
        projection.put("CumulativeTamperCount", 1);
        projection.put("InstantPushSetup", 1);
        projection.put("LoadLimitFunctionStatus", true);
        projection.put("LoadLimitvalueKW", 1);
        projection.put("MDVA", 1);
        projection.put("MDW", 1);
        projection.put("PhaseCurrent", 1);
        projection.put("SourceAddress", 1);
        projection.put("StructureSize", 1);
        projection.put("SystemTitle", 1);
        projection.put("TargetAddress", 1);
        projection.put("Voltage", 1);
        projection.put("WrapperLen", 1);
        return projection;
    }
}
