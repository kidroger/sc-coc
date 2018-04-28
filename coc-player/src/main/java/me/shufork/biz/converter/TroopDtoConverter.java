package me.shufork.biz.converter;

import me.shufork.biz.domain.CocHero;
import me.shufork.biz.domain.CocSpell;
import me.shufork.biz.domain.CocTroop;
import me.shufork.biz.utils.EntityKeyUtils;
import me.shufork.common.dto.supercell.coc.TroopDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class TroopDtoConverter {
    public static class ToCocTroop implements Converter<TroopDto,CocTroop>{
        @Override
        public CocTroop convert(MappingContext<TroopDto, CocTroop> mappingContext) {
            TroopDto source = mappingContext.getSource();
            CocTroop target = new CocTroop();
            target.setName(source.getName());
            target.setLevel(source.getLevel());
            target.setMaxLevel(source.getMaxLevel());
            target.setVillage(source.getVillage());
            // set id at last
            target.setId(EntityKeyUtils.keyOf(target));
            return target;
        }
    }
    public static class ToCocHero implements Converter<TroopDto,CocHero>{
        @Override
        public CocHero convert(MappingContext<TroopDto, CocHero> mappingContext) {
            TroopDto source = mappingContext.getSource();
            CocHero target = new CocHero();
            target.setName(source.getName());
            target.setLevel(source.getLevel());
            target.setMaxLevel(source.getMaxLevel());
            target.setVillage(source.getVillage());
            // set id at last
            target.setId(EntityKeyUtils.keyOf(target));
            return target;
        }
    }

    public static class ToCocSpell implements Converter<TroopDto,CocSpell>{
        @Override
        public CocSpell convert(MappingContext<TroopDto, CocSpell> mappingContext) {
            TroopDto source = mappingContext.getSource();
            CocSpell target = new CocSpell();
            target.setName(source.getName());
            target.setLevel(source.getLevel());
            target.setMaxLevel(source.getMaxLevel());
            target.setVillage(source.getVillage());
            // set id at last
            target.setId(EntityKeyUtils.keyOf(target));
            return target;
        }
    }
}
