package me.shufork.biz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.WarLogService;
import me.shufork.biz.vo.WarLogEntryVo;
import me.shufork.biz.vo.WarTeamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api(value = "/coc/war-log", description = "War Log API")
public class WarLogController {

    @Autowired
    private WarLogService warLogService;

    @ApiOperation(value = "参赛结果历史信息")
    @RequestMapping(value = "/coc/war-log/history/{tag}", method = RequestMethod.GET)
    public List<WarLogEntryVo> getWarResultHistory(
            @ApiParam(name = "tag", value = "部落tag", required = true) @PathVariable(name="tag") String clanTag){
        Page<WarLogEntryVo> result =  warLogService.getWarLog(clanTag,new PageRequest(0,50));
        return result.getContent();
        // FIXME : page api
    }
}
