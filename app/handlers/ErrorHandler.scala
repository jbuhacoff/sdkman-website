package handlers

import javax.inject.Singleton
import play.api.http.HttpErrorHandler
import play.api.mvc.Results._
import play.api.mvc._

import scala.concurrent._

@Singleton
class ErrorHandler extends HttpErrorHandler {

  def onClientError(request: RequestHeader, statusCode: Int, message: String) = statusCode match {
    case 404 =>
      Future.successful(NotFound(views.html.notfound()))
    case _ =>
      Future.successful(Status(statusCode)("A client error occurred: " + message))
  }

  def onServerError(request: RequestHeader, exception: Throwable) =
    Future.successful(InternalServerError("A server error occurred: " + exception.getMessage))

}

