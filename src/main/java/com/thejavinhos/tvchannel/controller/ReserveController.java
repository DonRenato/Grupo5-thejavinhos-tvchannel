package com.thejavinhos.tvchannel.controller;

import com.thejavinhos.tvchannel.entity.Actor;
import com.thejavinhos.tvchannel.entity.Reserve;
import com.thejavinhos.tvchannel.entity.ReserveRequest;
import com.thejavinhos.tvchannel.service.ActorService;
import com.thejavinhos.tvchannel.service.ReserveService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @GetMapping
    private ResponseEntity <List<Reserve>> list(){
        return ResponseEntity.ok(reserveService.listAll());
    }

//    @GetMapping("/{id}")
//    private ResponseEntity<List<Reserve>> listbyReservesById(@PathVariable int id){
//        return ResponseEntity.ok(reserveService.ator(id));
////        return ResponseEntity.ok();
//    }

    @PostMapping
    @CacheEvict(value = "reservas", allEntries = true)
    private ResponseEntity<Reserve> create(@RequestBody ReserveRequest reserve){
        return  ResponseEntity.ok(reserveService.createReserve(reserve));
    }

}
