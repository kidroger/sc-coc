package me.shufork.biz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.shufork.common.dto.supercell.coc.LeagueDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api(value = "/coc/leagues", description = "League API")
public class LeagueController {

    @ApiOperation(value = "联赛信息")
    @RequestMapping(value = "/coc/leagues/{id}", method = RequestMethod.GET)
    public LeagueDto getLeague(
            @ApiParam(name = "id", value = "联赛ID", required = true) @PathVariable(name="id") String id){
        //
        return null;
    }

    @ApiOperation(value = "所有联赛信息")
    @RequestMapping(value = "/coc/leagues", method = RequestMethod.GET)
    public List<LeagueDto> getLuegues(){
        //
        return null;
    }
}
