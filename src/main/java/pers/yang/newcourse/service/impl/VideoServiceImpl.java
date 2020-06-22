package pers.yang.newcourse.service.impl;

import pers.yang.newcourse.entity.Video;
import pers.yang.newcourse.mapper.VideoMapper;
import pers.yang.newcourse.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2020-06-13
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
