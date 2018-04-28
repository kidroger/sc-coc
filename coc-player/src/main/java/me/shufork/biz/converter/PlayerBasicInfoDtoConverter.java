package me.shufork.biz.converter;

import me.shufork.biz.domain.CocPlayer;
import me.shufork.common.constants.CocConstants;
import me.shufork.common.dto.supercell.coc.PlayerBasicInfoDto;
import me.shufork.common.utils.CocHashTagUtil;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class PlayerBasicInfoDtoConverter {
    public static class ToCocPlayer implements Converter<PlayerBasicInfoDto,CocPlayer>{

        @Override
        public CocPlayer convert(MappingContext<PlayerBasicInfoDto, CocPlayer> mappingContext) {
            PlayerBasicInfoDto source = mappingContext.getSource();
            CocPlayer target = new CocPlayer();
            target.setTag(CocHashTagUtil.ensurePrefix(source.getTag()));
            target.setName(source.getName());
            target.setRole(source.getRole());
            target.setExpLevel(source.getExpLevel());
            //CocLeague cocLeague = source.getLeague() ==null?null:modelMapper.map(source.getLeague(),CocLeague.class);
            target.setLeague(source.getLeague()==null? CocConstants.LEAGUE_ID_INVALID:source.getLeague().getId());
            target.setTrophies(source.getTrophies());
            target.setVersusTrophies(source.getVersusTrophies());
            target.setClanRank(source.getClanRank());
            target.setPreviousClanRank(source.getPreviousClanRank());
            target.setTotalDonations(source.getTotalDonations());
            target.setTotalDonationsReceived(source.getTotalDonationsReceived());
            //CocPlayerDetails details = modelMapper.map(source,CocPlayerDetails.class);
            //target.setDetails(details);
            return target;
        }
    }
}
