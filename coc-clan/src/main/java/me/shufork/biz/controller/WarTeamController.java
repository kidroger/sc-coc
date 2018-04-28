package me.shufork.biz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.WarTeamService;
import me.shufork.biz.vo.WarTeamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api(value = "/coc/war-team", description = "War Team API")
public class WarTeamController {

    @Autowired
    private WarTeamService warTeamService;

    @ApiOperation(value = "参赛队伍信息")
    @RequestMapping(value = "/coc/war-team/{id}", method = RequestMethod.GET)
    public WarTeamVo getWarTeamInfo(
            @ApiParam(name = "id", value = "参赛id", required = true) @PathVariable(name="id") String teamId){
        //
        return warTeamService.getOneById(teamId);
    }
    @ApiOperation(value = "参赛历史信息")
    @RequestMapping(value = "/coc/war-team/history/{tag}", method = RequestMethod.GET)
    public List<WarTeamVo> getWarTeamHistory(
            @ApiParam(name = "tag", value = "部落tag", required = true) @PathVariable(name="tag") String clanTag){
        return warTeamService.findAllByClanTag(clanTag);
    }
}
