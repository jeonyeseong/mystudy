package com.kh.spring.memo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.memo.model.dao.MemoDao;
import com.kh.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemoServiceImpl implements MemoService {
	
	@Autowired
	private MemoDao memoDao;

	@Override
	public List<Memo> memoList() {
		log.debug("서비스 주업무");
		return memoDao.memoList();
	}

	@Override
	public int insertMemo(Memo memo) {
		log.debug("서비스 주업무");
		return memoDao.insertMemo(memo);
	}

	@Override
	public int deleteMemo(int no) {
		log.debug("서비스 주업무");
		return memoDao.deleteMemo(no);
	}

	@Override
	public Memo selectOneMemo(int no) {
		return memoDao.selectOneMemo(no);
	}

}
