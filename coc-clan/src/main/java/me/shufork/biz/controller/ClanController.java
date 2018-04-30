package me.shufork.biz.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.components.HotClans;
import me.shufork.biz.service.ClanWebService;
import me.shufork.biz.vo.ClanInfoVo;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(value = "/coc/clans", description = "Clan API")
public class ClanController {

    @Autowired
    private ClanWebService clanWebService;
    @Autowired
    private HotClans hotClans;

    @ApiOperation(value = "部落基本信息")
    @RequestMapping(value = "/coc/clans/{tag}/basic-info", method = RequestMethod.GET)
    public ClanBasicInfoDto getClanBasicInfo(
            @ApiParam(name = "tag", value = "部落Tag", required = true) @PathVariable(name="tag") String tag){
        return clanWebService.getClanBasicInfo(tag);
    }

    @ApiOperation(value = "部落详细信息")
    @RequestMapping(value = "/coc/clans/{tag}/detailed-info", method = RequestMethod.GET)
    public ClanDetailedInfoDto getClanDetailedInfo(
            @ApiParam(name = "tag", value = "部落Tag", required = true) @PathVariable(name="tag") String tag){
        return clanWebService.getClanDetailedInfo(tag);
    }
    @ApiOperation(value = "拉取部落详细信息")
    @RequestMapping(value = "/coc/clans/pull", method = RequestMethod.GET)
    public void pullClanDetailedInfo(
            @ApiParam(name = "tag", value = "部落Tag", required = true) @RequestParam(name="tag") String tag){
        clanWebService.pullClanDetailedInfo(tag);
    }

    @ApiOperation(value = "推荐部落")
    @RequestMapping(value = "/coc/clans/hot", method = RequestMethod.GET)
    public List<ClanInfoVo> getHotClans(
            @ApiParam(name = "size", value = "数量", required = true) @RequestParam(name="size") int size){
        return hotClans.getHotClans(size);
    }

}
