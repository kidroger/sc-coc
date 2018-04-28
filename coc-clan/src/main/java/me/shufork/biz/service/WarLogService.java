package me.shufork.biz.service;

import me.shufork.biz.vo.WarLogEntryVo;
import me.shufork.common.dto.supercell.coc.WarLogEntryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarLogService {
    void updateWarLog(Iterable<WarLogEntryDto> warLogs);
    Page<WarLogEntryVo> getWarLog(String clanTag, Pageable pageable);
}
