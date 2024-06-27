package com.cueshop.service;

import com.cueshop.model.Cue;
import com.cueshop.repository.CueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CueService {
    @Autowired
    CueRepository cueRepository;
    @Transactional
    public void addNewCue(Cue cue){
        try {
            cueRepository.save(cue);
        } catch (Exception e){
            throw new RuntimeException("Thêm sản phẩm mới không thành công!");
        }
    }
    @Transactional
    public void updateCue(Cue cue){
        try {
            cueRepository.save(cue);
        } catch (Exception e){
            throw new RuntimeException("Cập nhật sản phẩm không thành công!");
        }
    }
    @Transactional
    public void deleteCue(Long id){
        try {
            cueRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Xóa sản phẩm không thành công!");
        }
    }
    public List<Cue> getAllCues(){
        return cueRepository.findAll();
    }
    public Cue getCue(Long id){
        return cueRepository.findCuesById(id);
    }
    public List<Cue> search(String query){
        List<Cue> cueSearch = cueRepository.findCuesByNameContainingOrBrandContainingOrSpecificationsContaining(query, query, query);
        return cueSearch;
    }
}
