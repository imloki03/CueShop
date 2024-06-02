package com.cueshop.controller;

import com.cueshop.model.Cue;
import com.cueshop.service.CueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class CueController {
    @Autowired
    CueService cueService;
    @PostMapping("/add_new_cue")
    public ResponseEntity<String> addNewCue(@RequestBody Cue cue){
        try{
            cueService.addNewCue(cue);
            return ResponseEntity.ok("Thêm sản phẩm mới thành công!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/update_cue")
    public ResponseEntity<String> updateCue(@RequestBody Cue cue){
        try{
            cueService.updateCue(cue);
            return ResponseEntity.ok("Cập nhật sản phẩm thành công!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/delete_cue")
    public ResponseEntity<String> deleteCue(@RequestBody Long id){
        try{
            cueService.deleteCue(id);
            return ResponseEntity.ok("Xóa sản phẩm thành công!");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/get_all_cue")
    public ResponseEntity<?> getAllCue(){
        try{
            return ResponseEntity.ok(cueService.getAllCues());
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/get_cue/{id}")
    public ResponseEntity<?> getCue(@PathVariable Long id){
        try {
            return ResponseEntity.ok(cueService.getCue(id));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
