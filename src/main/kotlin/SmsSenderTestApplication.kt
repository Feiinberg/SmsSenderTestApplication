

import ru.itech.latit.service.util.SmsSender

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val smsSender=SmsSender()
    val message = "SmsSender is working  correcttly"
    val to = "79204156906"


    smsSender.sendSms(message, to)


    //smsSender.stop()
    }
