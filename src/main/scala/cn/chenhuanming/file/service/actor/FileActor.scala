package cn.chenhuanming.file.service.actor

import akka.actor.{Actor, ActorLogging}
import cn.chenhuanming.file.service.actor.FileActor.{GetPublicFileUrl, RequireUploadToken}
import cn.chenhuanming.file.service.domain.{UploadToken}
import cn.chenhuanming.file.service.repo.QiNiuRepo

/**
  *
  * @author chenhuanming
  *         Created at 2018/6/10
  */
class FileActor extends Actor with ActorLogging {
  protected val config = context.system.settings.config

  private val accessKey = config.getString("qiniu.access-key")
  private val secretKey = config.getString("qiniu.secret-key")
  private val bucket = config.getString("qiniu.bucket")

  private val domain = config.getString("qiniu.domain")

  private val getUploadTokenByBucket = QiNiuRepo.getUploadToken(accessKey, secretKey, _: String)

  override def receive: Receive = {
    case RequireUploadToken =>
      sender ! UploadToken(getUploadTokenByBucket(bucket))
  }

}

object FileActor {

  case class RequireUploadToken()

  case class GetPublicFileUrl(filename: String)

}
