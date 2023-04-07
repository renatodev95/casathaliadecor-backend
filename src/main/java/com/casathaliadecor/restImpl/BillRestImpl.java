package com.casathaliadecor.restImpl;

import com.casathaliadecor.rest.BillRest;
import com.casathaliadecor.service.BillService;
import com.casathaliadecor.utils.StoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BillRestImpl implements BillRest {

    @Autowired
    BillService billService;

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        try {
            return billService.generateReport(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return StoreUtils.somethingWentWrongResponse();
    }
}
