package me.shufork.biz.converter;

import me.shufork.biz.domain.CocWarLog;
import me.shufork.biz.vo.WarLogEntryVo;
import me.shufork.common.utils.CocDateTimeUtil;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class WarLogEntryVoConverter {
    public static class FromCocWarLog implements Converter<CocWarLog,WarLogEntryVo>{

        @Override
        public WarLogEntryVo convert(MappingContext<CocWarLog, WarLogEntryVo> mappingContext) {
            CocWarLog source = mappingContext.getSource();
            WarLogEntryVo target = new WarLogEntryVo();
            target.setResult(source.getResult());
            target.setEndTime(CocDateTimeUtil.format(source.getEndTime()));
            target.setTeamSize(source.getTeamSize());
            target.setHomeTeam(source.getHomeTeam());
            target.setAwayTeam(source.getAwayTeam());
            return target;
        }
    }
}
