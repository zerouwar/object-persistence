package cn.chenhuanming.file.service

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.http.scaladsl.model.{StatusCodes}
import akka.http.scaladsl.server.{HttpApp, Route}
import akka.util.Timeout
import cn.chenhuanming.file.service.actor.FileActor
import cn.chenhuanming.file.service.actor.FileActor.RequireUploadToken
import cn.chenhuanming.file.service.domain.{ClientConfig, Domain, JsonSupport, UploadToken}

import scala.concurrent.duration._

/**
  *
  * @author chenhuanming
  *         Created at 2018/6/10
  */
object Server extends HttpApp with JsonSupport {
  val port = 8080

  /**
    * actor和system
    */
  private val mySystem = ActorSystem("mySystem")
  private val fileActor = mySystem.actorOf(Props[FileActor])

  /**
    * 客户端需要读取的配置
    */
  private val clientConfig = {
    val qiniuDomain = mySystem.settings.config.getString("qiniu.domain")
    ClientConfig(Domain(qiniuDomain))
  }

  implicit val timeout = Timeout(5.seconds)

  /**
    * 默认首页的路由
    */
  private val indexRoute = (get & path("")) {
    getFromResource("static/index.html")
  }

  /**
    * 静态资源路由
    */
  private val staticResources = (get & pathPrefix("static")) {
    (pathEndOrSingleSlash & redirectToTrailingSlashIfMissing(StatusCodes.TemporaryRedirect)) {
      getFromResource("static/index.html")
    } ~ {
      getFromResourceDirectory("static")
    }
  }

  /**
    * 全局资源，包括客户端配置等
    */
  private val globalResources = (get & pathPrefix("config")) {
    complete {
      clientConfig
    }
  }

  /**
    * 文件资源
    */
  private val fileResources =
    (get & path("uploadToken")) {
      onSuccess(fileActor ? RequireUploadToken) {
        case r: UploadToken => complete {
          r
        }
      }
    }

  override def routes: Route = {
    indexRoute ~ staticResources ~ globalResources ~ fileResources
  }


  def main(args: Array[String]): Unit = {
    Server.startServer("localhost", Server.port, mySystem)
    mySystem.terminate()
  }
}


