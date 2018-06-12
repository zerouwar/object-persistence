package cn.chenhuanming.file.service.repo

import cn.chenhuanming.file.service.domain.UploadToken
import com.qiniu.util.Auth

/**
  *
  * @author chenhuanming
  *         Created at 2018/6/9
  */
object QiNiuRepo {
  def getUploadToken(accessKey: String, secretKey: String, bucket: String): String = {
    val auth = Auth.create(accessKey, secretKey)
    auth uploadToken(bucket)
  }
}
