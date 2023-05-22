package cn.binarydumplings.cetexam.Mapper;

import cn.binarydumplings.cetexam.Bean.Answer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AnswerMapper extends BaseMapper<Answer> {

}
