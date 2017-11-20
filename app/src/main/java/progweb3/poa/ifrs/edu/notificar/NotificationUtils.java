package progweb3.poa.ifrs.edu.notificar;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.TaskStackBuilder;

import java.util.ArrayList;
import java.util.List;

public class NotificationUtils {
    public static final String ACAO_DELETE =
            "progweb3.poa.ifrs.edu.DELETE_NOTIFICACAO";
    public static final String ACAO_NOTIFICACAO =
            "progweb3.poa.ifrs.edu.ACAO_NOTIFICACAO";
    private static PendingIntent criarPendingIntent(
            Context ctx, String texto, int id) {
        Intent resultIntent = new Intent(ctx, DetalheActivity.class);
        resultIntent.putExtra(DetalheActivity.EXTRA_TEXTO, texto);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(ctx);
        stackBuilder.addParentStack(DetalheActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        return stackBuilder.getPendingIntent(
                id, PendingIntent.FLAG_UPDATE_CURRENT);
    }
    public static void criarNotificacaoSimples(Context ctx, String texto, int id) {
        PendingIntent resultPendingIntent = criarPendingIntent(ctx, texto, id);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx,"channel_id")
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setSmallIcon(R.drawable.ic_notificacao)
                        .setContentTitle("Simples "+ id)
                        .setContentText(texto)
                        .setContentIntent(resultPendingIntent);
        NotificationManagerCompat nm = NotificationManagerCompat.from(ctx);
        nm.notify(id, mBuilder.build());
    }
    public static void criarNotificacaoCompleta(Context ctx, String texto, int id) {

        PendingIntent pitAcao = PendingIntent.getBroadcast(
                ctx, 0, new Intent(ACAO_NOTIFICACAO), 0);
        PendingIntent pitDelete = PendingIntent.getBroadcast(
                ctx, 0, new Intent(ACAO_DELETE), 0);
        Bitmap largeIcon = BitmapFactory.decodeResource(
                ctx.getResources(), R.mipmap.ic_launcher);
        PendingIntent pitNotificacao= criarPendingIntent(ctx, texto, id);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx, "channel_id")
                        .setSmallIcon(R.drawable.ic_notificacao)
                        .setColor(Color.RED)
                        .setContentTitle("Completa")
                        .setContentText(texto)
                        .setTicker("Chegou uma notificação")
                        .setWhen(System.currentTimeMillis())
                        .setLargeIcon(largeIcon)
                        .setAutoCancel(true)
                        .setContentIntent(pitNotificacao)
                        .setDeleteIntent(pitDelete)
                        .setLights(Color.BLUE, 1000, 5000)
                        .setVibrate(new long[]{100, 500, 200, 800})
                         .setNumber(id)
                        .setFullScreenIntent(pitNotificacao, false)
                        .setSubText("Subtexto");

        PendingIntent acaoCustomizada = PendingIntent.getActivity(ctx, 0, new Intent(ctx, DetalheActivity.class),0);
        mBuilder.addAction(R.drawable.ic_acao_notificacao, "Ação Customizada", acaoCustomizada);
        NotificationManagerCompat nm = NotificationManagerCompat.from(ctx);
        nm.notify(id, mBuilder.build());
    }
    public static void criarNotificationBig(Context ctx, int idNotificacao) {
        int numero = 5;
        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Mensagens não lidas:");
        for (int i = 1; i <= numero; i++) {
            inboxStyle.addLine("Mensagem " + i);
        }
        inboxStyle.setSummaryText("Clique para exibir");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ctx, "channel_id")
                .setSmallIcon(R.drawable.ic_notificacao)
                .setColor(Color.RED)
                .setContentTitle("Notificação")
                .setContentText("Vários itens pendentes")
                .setContentIntent(criarPendingIntent(ctx, "Mensagens não lidas", -1))
                .setNumber(numero)
                .setStyle(inboxStyle);
        NotificationManagerCompat nm = NotificationManagerCompat.from(ctx);
        nm.notify(idNotificacao, mBuilder.build());
    }
}

