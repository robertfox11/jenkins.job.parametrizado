job('MiSegundoTrabajo') {
    description('Este es un trabajo creado con Job DSL')
  	scm {
        git ('https://github.com/robertfox11/jenkins.job.parametrizado.git', 'main') {
      		node / gitConfigName('roberfox11')
      		node / gitConfigEmail('11rsahome@gmail.com')
        }
    }
    parameters {
          stringParam('nombre', defaultValue = 'Robert', description = 'Parametro de cadena para el job')
          choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
          booleanParam('agente', false)
   }
   triggers {
          //cron('H/7 * * * *')
   }
   steps {
          shell("bash jobscript.sh")
   }
   publishers {
        mailer('reyfoxgame11@gmail.com', true, true)
        slackNotifier {
          notifyAborted(true)
          notifyEveryFailure(true)
          notifyNotBuilt(false)
          notifyUnstable(false)
          notifyBackToNormal(true)
          notifySuccess(false)
          notifyRepeatedFailure(false)
          startNotification(false)
          includeTestSummary(false)
          includeCustomMessage(false)
          customMessage(null)
          sendAs(null)
          commitInfoChoice('NONE')
          teamDomain(null)
          authToken(null)
        }
   }
}
