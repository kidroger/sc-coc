package me.shufork.biz.converter;

import me.shufork.biz.domain.CocPlayerAchievement;
import me.shufork.common.vo.PlayerAchievementsVo;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class PlayerAchievementsVoConverter {
    public static class ToCocPlayerAchievement implements Converter<PlayerAchievementsVo,CocPlayerAchievement> {
        @Override
        public CocPlayerAchievement convert(MappingContext<PlayerAchievementsVo, CocPlayerAchievement> mappingContext) {
            PlayerAchievementsVo source = mappingContext.getSource();
            CocPlayerAchievement target = new CocPlayerAchievement();
            target.setName(source.getName());
            target.setStars(source.getStars());
            target.setValue(source.getValue());
            target.setTarget(source.getTarget());
            target.setCompletionInfo(source.getCompletionInfo());
            target.setVillage(source.getVillage());
            // set id outside
            return target;
        }
    }
}
